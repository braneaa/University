package web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class BookDto extends BaseDto {
    private String serialNumber;
    private String name;
    private String genre;
    private String author;
}
