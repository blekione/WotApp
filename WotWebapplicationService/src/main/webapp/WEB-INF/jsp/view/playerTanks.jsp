<div class="row">
 <div class="col col-lg-8 col-md-8 col-sm-12 col-xs-12">
  <table class="table table-striped table-hover ">
  <thead>
  <tr>
   <th>Tier</th>
   <th>Tank Name</th>
   <th>Tank Nation</th>
   <th>Tank Type</th>
   <th>Tank WN8</th>
  </tr>
  </thead>
  <tbody>
   <c:forEach items="${tanksWN8}" var="tankWN8">
    <c:set var="tankId" value="${tankWN8.getTankId()}" />
    <tr>
     <td>${tankDescriptions.get(tankId).getTier()}</td>
     <td>${tankDescriptions.get(tankId).getName()}</td>
     <td>${tankDescriptions.get(tankId).getNation()}</td>
     <td>${tankDescriptions.get(tankId).getType()}</td>     
     <td style="background-color: ${tankWN8.getWN8Color()}"><fmt:formatNumber
        type="number" maxFractionDigits="2"
        value="${tankWN8.getTankWN8()}" /> (${tankWN8.getWN8Rank()})</td>
    </tr>
   </c:forEach>
   </tbody>
  </table>
  <div class="text-center">
   <ul class="pagination">
    <li <c:if test="${pageNumber == 1}"> class="disabled" </c:if>>
     <a href="
      <c:url value="player_tanks">
       <c:param name="id" value="${playerId}" />
       <c:param name="platform" value="${platform}" />
       <c:param name="page" value="${pageNumber - 1}" />                   
      </c:url>
     ">&laquo;</a>
    </li>
    <c:forEach var="i" begin="1" end="${numberOfPages}">
     <li <c:if test="${i == pageNumber}"> class="active" </c:if>>
      <a href="
       <c:url value="player_tanks">
        <c:param name="id" value="${playerId}" />
        <c:param name="platform" value="${platform}" />
        <c:param name="page" value="${i}" />                   
       </c:url>
      "><c:out value="${i}"/> </a>
     </li>
    </c:forEach>
    <li<c:if test="${pageNumber == numberOfPages}"> class="disabled" </c:if>>
     <a href="
      <c:url value="player_tanks">
       <c:param name="id" value="${playerId}" />
       <c:param name="platform" value="${platform}" />
       <c:param name="page" value="${pageNumber + 1}" />                   
      </c:url>
     ">&raquo;</a></li>
   </ul>
  </div>
 </div>
</div>
