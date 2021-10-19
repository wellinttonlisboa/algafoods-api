package br.com.algaworks.algafoods;

import java.util.List;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import br.com.algaworks.algafoods.controller.KitchenController;
import br.com.algaworks.algafoods.domain.Kitchen;

@SpringBootApplication
public class AlgafoodsApplication {

    public static void main(String[] args) {
        ApplicationContext appContext = new SpringApplicationBuilder(AlgafoodsApplication.class).web(WebApplicationType.NONE).run(args);

        KitchenController kitchenController = appContext.getBean(KitchenController.class);

        Kitchen kitchen1 = new Kitchen();
        kitchen1.setName("Brasileira");

        kitchenController.save(kitchen1);

        ResponseEntity<List<Kitchen>> kitchens = kitchenController.listAll();
        for (Kitchen kitchen : kitchens.getBody()) {
            System.out.println(kitchen.getId());
            System.out.println(kitchen.getName());
        }

        ResponseEntity<Kitchen> kitchen = kitchenController.findById(2);
        System.out.println(kitchen.getBody().getName());

    }

}
