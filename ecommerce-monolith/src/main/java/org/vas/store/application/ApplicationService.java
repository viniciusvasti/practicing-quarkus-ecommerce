package org.vas.store.application;

import java.util.Set;

import org.vas.store.presentation.dtos.ProductCategoryDTO;

public interface ApplicationService {
    Set<ProductCategoryDTO> listProductsCatalog();
}
