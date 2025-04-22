//package com.example.FeedBack.controller;
//
//import com.example.FeedBack.dto.AttendanceRequest;
//import com.example.FeedBack.service.AttendanceService;
//import jakarta.validation.Valid;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/attendance")
//public class AttendanceController {
//
//    private final AttendanceService attendanceService;
//
//    public AttendanceController(AttendanceService attendanceService) {
//        this.attendanceService = attendanceService;
//    }
//
//    @PostMapping("/mark")
//    public ResponseEntity<?> markAttendance(
//            @Valid @RequestBody AttendanceRequest request,
//            BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return buildValidationErrorResponse(bindingResult);
//        }
//
//        try {
//            return ResponseEntity.ok(
//                    attendanceService.markAttendance(
//                            request.getStudentId(),
//                            request.getClassRoomId(),
//                            request.getLatitude(),
//                            request.getLongitude()
//                    )
//            );
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(
//                    Map.of("error", e.getMessage())
//            );
//        }
//    }
//
//    private ResponseEntity<Map<String, String>> buildValidationErrorResponse(BindingResult bindingResult) {
//        Map<String, String> errors = new HashMap<>();
//        bindingResult.getFieldErrors().forEach(error ->
//                errors.put(error.getField(), error.getDefaultMessage())
//        );
//        return ResponseEntity.badRequest().body(errors);
//    }
//}