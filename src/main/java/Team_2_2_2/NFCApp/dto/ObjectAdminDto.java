package Team_2_2_2.NFCApp.dto;

public class ObjectAdminDto {
    private ObjectDto objectDto;
    private AdminDto adminDto;

    // Constructor to ensure both DTOs are initialized
    public ObjectAdminDto(ObjectDto objectDto, AdminDto adminDto) {
        this.objectDto = objectDto;
        this.adminDto = adminDto;
    }

    // Getters for encapsulation
    public ObjectDto getObjectDto() {
        return objectDto;
    }

    public AdminDto getAdminDto() {
        return adminDto;
    }

    public static class ObjectDto {
        private String objectName;
        private String objectDesc;
        private String objectLocation;
        private String NfcId;
        private Long objectId;

        public String getObjectName() {
            return objectName;
        }

        public String getObjectDesc() {
            return objectDesc;
        }

        public String getObjectLocation() {
            return objectLocation;
        }

        public Long getObjectId() {
            return objectId;
        }

        public String getNfcId() {
            return NfcId;
        }
    }

    public static class AdminDto {
        private Long adminId;
        private String username;
        private String password;

        public AdminDto(String username, String password) {
            this.username = username;
            this.password = password;
        }

        // getters and setters
        public Long getAdminId() {
            return adminId;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
