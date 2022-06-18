package hac.ex4.repo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(value = 0)
    private double amount;

    @CreationTimestamp
    private Date date;

    public Payment() {}

    public Payment(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" + "id=" + id + ", amount=" + amount +  '}';
    }
}
