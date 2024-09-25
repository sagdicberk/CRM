package com.sgdcbrk.crm.repository;

import com.sgdcbrk.crm.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
