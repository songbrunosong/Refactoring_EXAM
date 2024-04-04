package com.brunosong.refactoring_exam.commonservice.controller;

import com.brunosong.refactoring_exam.commonservice.common.ConstantsManager;
import com.brunosong.refactoring_exam.commonservice.common.util.APIExcelDefaultReader;
import com.brunosong.refactoring_exam.commonservice.common.util.MetaBulkUtil;
import com.brunosong.refactoring_exam.commonservice.domain.MemberVo;
import com.brunosong.refactoring_exam.commonservice.domain.MetaBulkParam;
import com.brunosong.refactoring_exam.commonservice.domain.MetaBulkVo;
import com.brunosong.refactoring_exam.commonservice.handler.ex.MetabulkCustomApiException;
import com.brunosong.refactoring_exam.commonservice.response.MetaBulkResponseStatus;
import com.brunosong.refactoring_exam.commonservice.service.MetaExcelUploadCommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import static com.brunosong.refactoring_exam.commonservice.response.MetaBulkResponseStatus.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/metabulk")
public class MetaBulkLevelController {

    private final MessageSource messageSource;

    private final MetaExcelUploadCommonService commonService;


    @PostMapping("/metabulkInsert")
    public ResponseEntity<MetaBulkResponseStatus> metabulkInsert(@RequestParam("bulkFile") MultipartFile bulkFile ,
                                                                 @ModelAttribute MetaBulkParam param,
                                                                 @SessionAttribute(ConstantsManager.SESSION_KEY_MEMBER) MemberVo member) {
        param.setAdmId(member.getMemberCode());

        //1. 엑셀 맵핑후 인썰트 한다.
        List<LinkedHashMap<String, Object>> excelMapList;
        try {
            excelMapList = new APIExcelDefaultReader().getSheetData(bulkFile.getInputStream());
        } catch (Exception e) {
            throw new MetabulkCustomApiException(e.getMessage());
        }

        /* 리팩토링 대상 */
        List<MetaBulkVo> metaBulkVoList = commonService.mapToMetaBulkConvert(excelMapList);

        ////////////////////////////실제 로직////////////////////////////
        ////////////MetaBulkParam은 여기서 쓰임//////////////////////////


        return new ResponseEntity<>(
                MetaBulkUtil.setupSuccessResponse(Status.SUCCESS, messageSource.getMessage("metabulk.success", new String[]{}, Locale.getDefault())),
                HttpStatus.OK
        );

    }

}
