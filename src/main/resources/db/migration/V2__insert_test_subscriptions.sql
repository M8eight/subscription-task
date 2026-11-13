insert into user_subscriptions (user_login, subscription_status, subscription_expires_at) values
    ('ivanov_i', 'FREE', null),
    ('gref_g', 'PAID', now() + interval '30 days'),
    ('koch_a', 'PAID', now() - interval '1 days')
on conflict (user_login) do nothing;
