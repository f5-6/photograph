package com.photograph.backend.photograph.domain

class PhotographNotFoundException(msg: String = "사진 정보를 찾을 수 없습니다.") : RuntimeException(msg)
