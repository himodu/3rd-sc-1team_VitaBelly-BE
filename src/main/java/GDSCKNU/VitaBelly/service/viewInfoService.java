package GDSCKNU.VitaBelly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import GDSCKNU.VitaBelly.dto.*;
import GDSCKNU.VitaBelly.repository.*;
import GDSCKNU.VitaBelly.entity.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class viewInfoService {

    private final examInfoRepository examInfoRepository;
    private final medicineInfoRepository medicineInfoRepository;
    private final selfImpInfoRepository selfImpInfoRepository;

    public List<examInfoDto> getAlllexamInfo(){

    }
    public examInfoDto getExamInfo(int id){

    }

    public List<medicineInfoDto> getAllmedicineInfo(String sortBy){

    }

    public medicineInfoDto getMedicineInfo(int id){

    }

    public List<selfImpInfoDto> getAllselfImpInfo(){

    }

}
