package com.coverflow.notification.presentation;

import com.coverflow.notification.application.NotificationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureRestDocs
@WebMvcTest(NotificationController.class)
public class NotificationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // NotificationController 가 의존하는 빈을 모킹
    private NotificationService notificationService;

    @Nested
    @DisplayName("알림 연결 시")
    class connectNotification {

        @Test
        @DisplayName("알림 연결에 성공한다.")
        void success() {
            //given


            //when


            //then
        }
    }
}
