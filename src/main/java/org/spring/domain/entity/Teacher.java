package org.spring.domain.entity;

import lombok.*;

/**
 * @author jianglong.li
 * @date 2021-06-12 14:26
 **/

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher extends Person{

    private Long number;

}
