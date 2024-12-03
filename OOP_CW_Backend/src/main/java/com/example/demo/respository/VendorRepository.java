package com.example.demo.respository;

import com.example.demo.models.Vendor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VendorRepository extends JpaRepository<Vendor, String> {
    Vendor findByVendorId(String vendorId);

    @Query(value = "SELECT tickets_by_vendor FROM vendor WHERE vendor_id = :vendor_id", nativeQuery = true)
    int getTicketsByVendorId(@Param("vendor_id")String vendorId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE vendor SET tickets_by_vendor = :tickets WHERE vendor_id = :vendor_id", nativeQuery = true)
    int AddTicketsByVendorId(@Param("vendor_id") String vendorId, @Param("tickets") int tickets);

    @Query(value = "SELECT * FROM vendor WHERE vendor_name= :vendorName", nativeQuery = true)
    Vendor findVendorByVendorName(@Param("vendorName") String vendorName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE vendor SET tickets_by_vendor = :ticketsSold", nativeQuery = true)
    int sellTickets(@Param("ticketsSold") int ticketsSold);
}
