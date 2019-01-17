package learn.ssm.pojo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * 实体类Cat
 * @author : Lin Can
 * @date : 2019/1/17 9:22
 */
@Setter
@Getter
@Component
public class Cat {
    private String name;
    private Integer age;
    private String owner;

}
