
<div class="container-fluid">
 <div class="row">
  <div
   class="panel panel-default bhoechie-tab-container col col-lg-6 col-md-6 col-sm-9 col-xs-12">
   <div class="panel-heading">
    <h4>${playerProfile.getPlayerMisc().getNickname()}</h4>
   </div>
   <div class="panel-body bhoechie-tab-container">
    <div class="row">
     <div class="col col-lg-2 col-md-2 col-sm-2 col-xs-2 bhoechie-tab-menu">
      <div class="list-group">
       <a href="#" class="list-group-item active text-center">
        <h4 class="glyphicon glyphicon-tag"></h4> <br /> <span
        class="hidden-xs">Overview</span>
       </a> <a href="#" class="list-group-item text-center">
        <h4 class="glyphicon glyphicon-screenshot"></h4> <br /> <span
        class="hidden-xs">Damage</span>
       </a> <a href="#" class="list-group-item text-center">
        <h4 class="glyphicon glyphicon-star"></h4> <br /> <span
        class="hidden-xs">Experience</span>
       </a> <a href="#" class="list-group-item text-center">
        <h4 class="glyphicon glyphicon-fire"></h4> <br /> <span
        class="hidden-xs">Battles</span></a> 
       <!--  <a href="#" class="list-group-item text-center">
        <h4 class="glyphicon glyphicon-option-horizontal"></h4> <br />
        <span class="hidden-xs">Misc</span>
       </a>  -->
      </div>
     </div>
     <div class="col col-lg-10 col-md-10 col-sm-10 col-xs-10 bhoechie-tab">
      <div class="bhoechie-tab-content active panel panel-default">
       <table class="table table-striped">
        <caption class="my_tab">Overview</caption>
        <tr>
         <td>In game since</td>
         <td>${playerProfile.getInGameSinceDate()}</td>
        </tr>
        <tr>
        <tr>
         <td>Total games</td>
         <td>${playerProfile.getGamesPlayed()}</td>
        </tr>
        <tr>
         <td>Win ratio</td>
         <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${playerProfile.getWinRatio()}" />&#37;</td>
        </tr>
        <tr>
         <td>Kills to deaths ratio</td>
         <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${playerProfile.getKillToDeathRatio()}" /></td>
        </tr>
        <tr>
         <td>Kills per game</td>
         <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${playerProfile.getKillsPerGameRatio()}" /></td>
        </tr>
        <tr>
         <td>Experience per game</td>
         <td>${playerProfile.getAverageExperience()}</td>
        </tr>
        <tr>
         <td>Average damage</td>
         <td>${playerProfile.getAverageDamage()}</td>
        <tr>
         <td>WN8</td>
         <td style="background-color: ${playerWN8.getWN8Color()} "><fmt:formatNumber type="number" maxFractionDigits="2" value="${playerWN8.getWn8()}" />  (${playerWN8.getWN8Rank()})</td>
        </tr>
       </table>
      </div>
      <div class="bhoechie-tab-content panel panel-default">
       <table class="table table-striped">
        <c:set var="damage" value="${playerProfile.getPlayerDamage()}" />
        <caption class="my_tab">Damage</caption>
        <tr>
         <td>Damage dealt to damage received ratio</td>
         <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${damage.getDamageDealt() / damage.getDamageReceived()}" /></td>
        </tr>
        <tr>
         <td>Total damage dealt by the player</td>
         <td>${damage.getDamageDealt()}</td>
        </tr>
        <tr>
         <td>Average damage dealt by the player's</td>
         <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${damage.getDamageDealt() / playerProfile.getGamesPlayed()}" /></td>
        </tr>
        <tr>
         <td>Total damage received by the player</td>
         <td>${damage.getDamageReceived()}</td>
        </tr>
        <tr>
         <td>Average damage dealt by the player's</td>
         <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${damage.getDamageReceived() / playerProfile.getGamesPlayed()}" /></td>
        </tr>
        <tr>
         <td>Total damage after a player's spot</td>
         <td>${damage.getDamageAfterSpot()}</td>
        </tr>
        <tr>
         <td>Average damage after a player's spot</td>
         <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${damage.getDamageAfterSpot() / playerProfile.getGamesPlayed()}" /></td>
        </tr>
        <tr>
         <td>Total damage after a player's detrack</td>
         <td>${damage.getDamageAfterTrack()}</td>
        </tr>
        <tr>
         <td>Average damage after a player's detrack</td>
         <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${damage.getDamageAfterTrack() / playerProfile.getGamesPlayed()}" /></td>
        </tr>
       </table>
      </div>
      <div class="bhoechie-tab-content panel panel-default">
       <table class="table table-striped">
        <c:set var="experience" value="${playerProfile.getPlayerExperience()}" />
        <caption class="my_tab">Experience</caption>
        <tr>
         <td>Total experience</td>
         <td>${experience.getTotalExperience()}</td>
        </tr>
        <tr>
         <td>Average experience</td>
         <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${experience.getTotalExperience() / damage.getDamageReceived()}" /></td>
        </tr>
        <tr>
         <td>Highest experience in battle</td>
         <td>${experience.getHighestExperience()}</td>
        </tr>
        <tr>
         <td>Tank with the highest experience in battle</td>
         <td>${experience.getHighestExpTankId()}</td>
        </tr>
       </table>
      </div>      
      <div class="bhoechie-tab-content panel panel-default">
       <table class="table table-striped">
        <c:set var="battles" value="${playerProfile.getPlayerGames()}" />
        <c:set var="frags" value="${playerProfile.getPlayerFrags()}" />
        <c:set var="misc" value="${playerProfile.getPlayerMisc()}" />
        <caption class="my_tab">Battles</caption>
        <tr>
         <td>Total battles</td>
         <td>${battles.getBattlesCount()}</td>
        </tr>
        <tr>
         <td>Won battles</td>
         <td>${battles.getBattlesWins()}</td>
        </tr>
        <tr>
         <td>Lost battles</td>
         <td>${battles.getBattlesLosses()}</td>
        </tr>
        <tr>
         <td>Drawn battles</td>
         <td>${battles.getBattlesDraws()}</td>
        </tr>
        <tr>
         <td>Survived battles</td>
         <td>${battles.getBattlesSurvived()} (<fmt:formatNumber type="number" maxFractionDigits="2" value="${battles.getBattlesSurvived() / playerProfile.getGamesPlayed() * 100}" />&#37;)</td>
        </tr>
        <tr>
         <td>Total frags</td>
         <td>${frags.getKills()}</td>
        </tr>
        <tr>
         <td>Frags per battle</td>
         <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${frags.getKills() / playerProfile.getGamesPlayed()}" /></td>
        </tr>
        <tr>
         <td>Base defence points per battle</td>
         <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${misc.getBaseDefensePoints() / playerProfile.getGamesPlayed()}" /></td>
        </tr>
        <tr>
         <td>Base defence points per battle</td>
         <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${misc.getBaseCapturePoints() / playerProfile.getGamesPlayed()}" /></td>
        </tr>
       </table>
      </div>      
     </div>
    </div>
   </div>
  </div>
  <div class= "col col-lg-2 col-md-2 col-sm-4 col-xs-8">
   <a href="
    <c:url value="player_tanks">
     <c:param name="id" value="${playerProfile.getPlayerId()}" />
     <c:param name="platform" value="${playerProfile.getPlatform()}" />
     <c:param name="page" value="1" />                   
    </c:url>
   " class="btn btn-primary btn-lg btn-vertical">WN8 per tank</a>
   <a href="
    <c:url value="session_wn8">
     <c:param name="id" value="${playerProfile.getPlayerId()}" />
     <c:param name="platform" value="${playerProfile.getPlatform()}" />
     <c:param name="action" value="none" />
    </c:url>
   " class="btn btn-primary btn-lg btn-vertical">Session WN8</a>
  </div>
 </div>
</div>
  <script
   src="<%=application.getInitParameter("resources")%>/js/verticalTabs.js"
   type="text/javascript"> </script>