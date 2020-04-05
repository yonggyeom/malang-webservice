package com.malang.webservice.mobile.domain.messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessagesRepository extends JpaRepository<Messages, Long> {

}
