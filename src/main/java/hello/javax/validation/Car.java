package hello.javax.validation;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Junwen on 6/17/15.
 */

@Data
public class Car {
    @NotNull
    private String manufacturer;

    @NotNull
    @Size(min = 2, max = 14)
    @CheckCase(caseMode = CaseMode.UPPER)
    private String licensePlate;

    @NonNull
    private String sex;

    @Min(2)
    private int seatCount;

    public Car(String manufacturer, String licencePlate, int seatCount, /*@NonNull*/ String sex) {

        this.manufacturer = manufacturer;
        this.licensePlate = licencePlate;
        this.seatCount = seatCount;
        this.sex = sex;
    }

}
