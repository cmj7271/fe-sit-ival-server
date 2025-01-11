```mermaid
    erDiagram
    FESTIVAL_MEMBER }o--o| FESTIVAL : ""
    MEMBER |o--o{ FESTIVAL_MEMBER : "" 
    MEMBER |o--|| SEAT : ""
    FESTIVAL |o--o{ SEAT : ""
    
    FESTIVAL {
        int id PK
        string name "행사 이름"
        LocalDate day "YYYY-MM-DD"
        LocalTime time "HH:MM"
    }
    SEAT {
        int id PK
        int member_id FK 
        int postion "좌석 번호"
    }
    MEMBER {
        int id PK
        string name
    }
    FESTIVAL_MEMBER {
        int id PK
        int member_id FK
        int festival_id FK
    }
```