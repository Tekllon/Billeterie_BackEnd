package com.Alex.billeterie;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

interface TribuneRepository extends JpaRepository<Tribune, Long> {


}
