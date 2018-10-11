package com.qualification.app.model;

import com.qualification.app.enums.RequestType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "SIMPLE_REQUEST")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "dataAndTypeBuilder")
@ToString(exclude = {"createdDate", "modifiedDate"})
public class SimpleRequest {

    @Id
    @Column(name = "REQ_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REQ_SEQ")
    @SequenceGenerator(name = "REQ_SEQ", initialValue = 1, sequenceName = "REQ_SEQ")
    private long id;

    @Column(name = "REQ_DATA")
    @Getter
    @Setter
    private String data;

    @Column(name = "REQ_CREATED_DATE", nullable = false, updatable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdDate;

    @Enumerated
    @Column(name = "REQ_TYPE")
    @Getter
    @Setter
    private RequestType requestType;

    @Column(name = "REQ_UPDATED_DATE")
    @Temporal(value = TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date modifiedDate;

    public static SimpleRequestBuilder builder(String data, RequestType requestType) {
        return dataAndTypeBuilder().data(data).requestType(requestType);
    }
}
