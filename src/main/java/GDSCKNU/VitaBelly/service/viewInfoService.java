package GDSCKNU.VitaBelly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import GDSCKNU.VitaBelly.dto.*;
import GDSCKNU.VitaBelly.repository.*;
import GDSCKNU.VitaBelly.entity.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class viewInfoService {

    private final examInfoRepository examInfoRepository;
    private final medicineInfoRepository medicineInfoRepository;
    private final selfImpInfoRepository selfImpInfoRepository;

    public List<examInfoDto> getAlllexamInfo(){
            List<examInfo> examInfoList = examInfoRepository.findAll();
            List<examInfoDto> examInfoDtos = new ArrayList<>();

            for(examInfo e : examInfoList){

                examInfoDto dto = examInfoDto.builder()
                        .id(e.getId().intValue())
                        .startWeek(e.getStartWeek())
                        .endWeek(e.getEndWeek())
                        .examType(e.getExamType())
                        .explane(e.getExplane())
                        .build();
                examInfoDtos.add(dto);
            }
            return examInfoDtos;
    }
    public examInfoDto getExamInfo(int id){
        Optional<examInfo> target = examInfoRepository.findById(Long.valueOf(id));
        examInfo result;
        if(target.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else{
            result = target.get();
        }
        return examInfoDto.builder()
                .id(result.getId().intValue())
                .startWeek(result.getStartWeek())
                .endWeek(result.getEndWeek())
                .examType(result.getExamType())
                .explane(result.getExplane())
                .build();
    }

    public List<medicineInfoDto> getAllmedicineInfo(String sortBy){

        List<medicineInfo>  medicineInfoList;

        if(sortBy.equals("grade")) {
            medicineInfoList = medicineInfoRepository.findAll(Sort.by(Sort.Direction.ASC,"grade"));
        }else if(sortBy.equals("name")){
            medicineInfoList = medicineInfoRepository.findAll(Sort.by(Sort.Direction.ASC,"medicineName"));
        }else if(sortBy.equals("company")){
            medicineInfoList = medicineInfoRepository.findAll(Sort.by(Sort.Direction.ASC,"medicineCom"));
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        List<medicineInfoDto> medicineInfoDtos = new ArrayList<>();

        for(medicineInfo e : medicineInfoList){
            medicineInfoDto dto = medicineInfoDto.builder()
                    .id(e.getId().intValue())
                    .grade(e.getGrade())
                    .imageLink(e.getImageLink())
                    .medicineName(e.getMedicineName())
                    .medicineCom(e.getMedicineCom())
                    .build();
            medicineInfoDtos.add(dto);
        }
        return medicineInfoDtos;
    }

    public medicineInfoDto getMedicineInfo(int id){
        Optional<medicineInfo> target = medicineInfoRepository.findById(Long.valueOf(id));
        medicineInfo result;
        if(target.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }else{
            result = target.get();
        }
        return  medicineInfoDto.builder()
                .id(result.getId().intValue())
                .grade(result.getGrade())
                .imageLink(result.getImageLink())
                .medicineName(result.getMedicineName())
                .medicineCom(result.getMedicineCom())
                .build();

    }

    public List<selfImpInfoDto> getAllselfImpInfo(){
        List<selfImpInfo>  selfImpInfos = selfImpInfoRepository.findAll();
        List<selfImpInfoDto> selfImpInfoDtos = new ArrayList<>();

        for(selfImpInfo e: selfImpInfos){
            selfImpInfoDto dto = selfImpInfoDto.builder()
                    .id(e.getId().intValue())
                    .selfImpType(e.getSelfImpType())
                    .detailList(e.getDetailList())
                    .build();

            selfImpInfoDtos.add(dto);
        }
        return selfImpInfoDtos;
    }

}
