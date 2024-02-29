package com.coverflow.member.presentation;

import com.coverflow.global.annotation.AdminAuthorize;
import com.coverflow.global.annotation.MemberAuthorize;
import com.coverflow.global.handler.ResponseHandler;
import com.coverflow.member.application.MemberService;
import com.coverflow.member.dto.request.SaveMemberInfoRequest;
import com.coverflow.member.dto.response.FindAllMembersResponse;
import com.coverflow.member.dto.response.FindMemberInfoResponse;
import com.coverflow.member.dto.response.UpdateNicknameResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/find-member")
    @MemberAuthorize
    public ResponseEntity<ResponseHandler<FindMemberInfoResponse>> findMemberById(
            @AuthenticationPrincipal final UserDetails userDetails
    ) {
        return ResponseEntity.ok()
                .body(ResponseHandler.<FindMemberInfoResponse>builder()
                        .statusCode(HttpStatus.OK)
                        .data(memberService.findMemberById(userDetails.getUsername()))
                        .build());
    }

    @GetMapping("/admin/find-members")
    @AdminAuthorize
    public ResponseEntity<ResponseHandler<List<FindAllMembersResponse>>> findAllMemberById(
            @RequestParam(defaultValue = "0", value = "pageNo") @Valid final int pageNo,
            @RequestParam(defaultValue = "createdAt", value = "criterion") @Valid final String criterion
    ) {
        return ResponseEntity.ok()
                .body(ResponseHandler.<List<FindAllMembersResponse>>builder()
                        .statusCode(HttpStatus.OK)
                        .data(memberService.findAllMembers(pageNo, criterion))
                        .build());
    }

    @GetMapping("/admin/find-by-status")
    @AdminAuthorize
    public ResponseEntity<ResponseHandler<List<FindAllMembersResponse>>> findMembersByStatus(
            @RequestParam(defaultValue = "0", value = "pageNo") @Valid final int pageNo,
            @RequestParam(defaultValue = "createdAt", value = "criterion") @Valid final String criterion,
            @RequestParam(defaultValue = "등록", value = "status") @Valid final String status
    ) {
        return ResponseEntity.ok()
                .body(ResponseHandler.<List<FindAllMembersResponse>>builder()
                        .statusCode(HttpStatus.OK)
                        .data(memberService.findMembersByStatus(pageNo, criterion, status))
                        .build()
                );
    }

    @PostMapping("/save-member-info")
    public ResponseEntity<ResponseHandler<Void>> saveMemberInfo(
            @AuthenticationPrincipal final UserDetails userDetails,
            @RequestBody @Valid final SaveMemberInfoRequest request
    ) {
        memberService.saveMemberInfo(userDetails.getUsername(), request);
        return ResponseEntity.ok()
                .body(ResponseHandler.<Void>builder()
                        .statusCode(HttpStatus.OK)
                        .build());
    }

    @PostMapping("/update-nickname")
    @MemberAuthorize
    public ResponseEntity<ResponseHandler<UpdateNicknameResponse>> updateNickname(
            @AuthenticationPrincipal final UserDetails userDetails
    ) {
        memberService.updateNickname(userDetails.getUsername());
        return ResponseEntity.ok()
                .body(ResponseHandler.<UpdateNicknameResponse>builder()
                        .statusCode(HttpStatus.OK)
                        .data(memberService.updateNickname(userDetails.getUsername()))
                        .build());
    }

    @PostMapping("/logout")
    @MemberAuthorize
    public ResponseEntity<ResponseHandler<Void>> logout(
            @AuthenticationPrincipal final UserDetails userDetails
    ) {
        memberService.logout(userDetails.getUsername());
        return ResponseEntity.ok()
                .body(ResponseHandler.<Void>builder()
                        .statusCode(HttpStatus.OK)
                        .build());
    }

    @PostMapping("/leave")
    @MemberAuthorize
    public ResponseEntity<ResponseHandler<Void>> deleteMember(
            @AuthenticationPrincipal final UserDetails userDetails
    ) {
        memberService.leaveMember(userDetails.getUsername());
        return ResponseEntity.ok()
                .body(ResponseHandler.<Void>builder()
                        .statusCode(HttpStatus.OK)
                        .build());
    }
}
