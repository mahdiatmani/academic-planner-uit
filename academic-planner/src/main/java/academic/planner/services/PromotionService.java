package academic.planner.services;


import academic.planner.entities.Promotion;
import academic.planner.repositories.PromotionRepository;
import academic.planner.utils.AcademicPlannerException;
import academic.planner.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {
    protected final PromotionRepository promotionRepository;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public List<Promotion> getAll() {
        return promotionRepository.findAll();
    }

    public Promotion getById(Long id) {
        Optional<Promotion> optionalPromotion = promotionRepository.findById(id);
        if(! optionalPromotion.isPresent()) throw new AcademicPlannerException(ErrorCode.promotion_not_found, "Promotion not found with id => " + id);
        return optionalPromotion.get();
    }

    public Promotion save(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public List<Promotion> save(List<Promotion> promotions) {
        List<Promotion> promotionsList = new ArrayList<Promotion>();
        for (Promotion promotion: promotions) {
            promotionsList.add(promotionRepository.save(promotion));
        }
        return promotionsList;
    }

    public void delete(Long id) {
        promotionRepository.delete(getById(id));
    }
}
