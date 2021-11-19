package com.yoco.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Contact implements Serializable {

    private static final long serialVersionUID = -2128618297818287420L;

    @Id
    private String id;

    @Index
    private String email;

    @Index
    private String password;

    @Index
    private Long dateAddedLongTime = new Date().getTime();

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @Index
    private List<String> addresses = new ArrayList<>();

}
