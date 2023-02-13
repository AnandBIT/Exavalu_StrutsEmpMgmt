<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach var="district" items="${districtList}">
    <option value="${district.getDistrictCode()}" ${district.getDistrictCode() == user.getDistrictCode() ? "selected" : ""}>
        ${district.getDistrictName()}
    </option>
</c:forEach>