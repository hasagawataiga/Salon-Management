package com.salon.manager.dto.requests;

public record LoginRequest(
    String email,
    String password){}
