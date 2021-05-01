package model;

import java.time.LocalDate;

public class Subscription {
	private final Integer userId;
    private final Integer tariffId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private SubscriptionStatus status;
}
