package be.darkshark.parkshark.domain.entity.util;

public enum MemberShipLevel {
    BRONZE (0, 0, 4),
    SILVER (10, 20, 6),
    GOLD(40, 30, 24) ;

    private final int maxAllocationTime;
    private final int monthlyCost;
    private final int reduction;

    MemberShipLevel(int monthlyCost, int reduction, int maxAllocationTime) {
        this.maxAllocationTime = maxAllocationTime;
        this.monthlyCost = monthlyCost;
        this.reduction = reduction;
    }
}
