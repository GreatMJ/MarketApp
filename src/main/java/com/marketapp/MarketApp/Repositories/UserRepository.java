package com.marketapp.MarketApp.Repositories;

import com.marketapp.MarketApp.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<AppUser, UUID> {
}
