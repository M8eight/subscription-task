create table user_subscriptions (
    user_login           varchar(100) not null primary key,
    subscription_status  varchar(10) not null default 'FREE'
        constraint chk_subscription_status check (subscription_status in ('FREE', 'PAID')),
    created_at timestamptz not null default now(),
    subscription_expires_at timestamptz,

    constraint chk_paid_has_expired check (subscription_status = 'FREE'
        or subscription_expires_at is not null)
);

create index idx_paid_expires_at on user_subscriptions (subscription_expires_at) where subscription_status = 'PAID'