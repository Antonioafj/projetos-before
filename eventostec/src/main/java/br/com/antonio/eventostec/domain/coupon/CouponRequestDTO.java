package br.com.antonio.eventostec.domain.coupon;

public record CouponRequestDTO(String code, Integer discount, Long valid) {
}
