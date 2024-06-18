package practice;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CityIndex {
    private int city;
    private int latitude;
    private int longitude;
    private int country;
    private int population;
    private int id;
}
