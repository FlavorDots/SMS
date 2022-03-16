package com.project.StudentManagement.domain.identity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentIdentity implements Serializable {

    @NotNull
    private Long studentNumberID;

    @NotNull
    private String schoolCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentIdentity that = (StudentIdentity) o;
        if (!studentNumberID.equals(that.studentNumberID)) return false;
        return schoolCode.equals(that.schoolCode);
    }

    @Override
    public int hashCode() {
        int result = studentNumberID.hashCode();
        result = 31 * result + schoolCode.hashCode();
        return result;
    }
}
