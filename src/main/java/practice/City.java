package practice;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {
    private String city;
    private double latitude;
    private double longitude;
    private String country;
    private int population;
}
