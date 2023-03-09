package com.ssafy.woonana.error.exception.custom;

public class ParticipationIsFullException extends RuntimeException {
    private static final String MESSAGE = "모집이 마감되었습니다.";
    public ParticipationIsFullException() { super(MESSAGE); }
}
