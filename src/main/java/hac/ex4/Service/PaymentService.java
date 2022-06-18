package hac.ex4.Service;

import hac.ex4.repo.Payment;
import hac.ex4.repo.PaymentRepository;
import hac.ex4.repo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

     public void add(Product product){
         Payment p = new Payment();
         p.setAmount(product.getPrice()*product.getQuantity());
         paymentRepository.save(p);
     }
}
