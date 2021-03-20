package com.pdp.demo.repository;

import com.pdp.demo.entity.Company;
import com.pdp.demo.payload.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("select new com.pdp.demo.payload.Response(a.homeNumber,a.streetName,c.email, c.phoneNumber,c.telegram,c.website, comp.name ,c2.name)from Company  comp join Address a on comp.address.id= a.id join District d on d.id = a.district.id  join Region  r on r.id = d.region.id join Contact c on comp.contact.id = c.id join Course c2 on comp.id = c2.company.id where r.name =?1 and c2.name =?2")
    Response findCompany(String regionName, String courseName);

}
