<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html lang="${language}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pariroom</title>

<link href="/jsp/css/reg.css" rel="stylesheet">

<script src="http://code.jquery.com/jquery-1.11.0.js"></script>
<script src="/js/reg.js"></script>
</head>
<body>

<div class="form_cont">


<div class="login_form">

<h3><fmt:message key="reg.name"/>
</h3>

<h2><fmt:message key="reg.title"/>
</h2>

    <c:if test="${not empty fail}">
        <h3 class="register-error"><fmt:message key="${fail}"/></h3>
    </c:if>
    <form role="form" method="post" action="/controller"/>
    <input type="hidden" name="command" value="signup"/>
<ul>

<li>
<table class="F_Table">
<tbody>
  <tr>
    <td class="F_Lcol"><fmt:message key="reg.login"/></td>
    <td class="F_Req">*</td>
    <td class="F_Val"> <input class="login" name="login" type="text" title = "Choose your login from 3 to 16 characters" size="30" name="login" required autocomplete="off" pattern="[A-z0-9_-]{3,16}" ></td>
    <td class="F_Erms"></td>
    </tr>
    </tbody>
    </table>
  </li>


    <li>
<table class="F_Table">
<tbody>
  <tr>
    <td class="F_Lcol"><fmt:message key="reg.lname"/>
    </td>
    <td class="F_Req">*</td>
    <td class="F_Val"> <input class="last-name"  type="text" name="last-name" size="30" required autocomplete="off" pattern="[A-zА-я]+"></td>
    <td class="F_Erms"></td>
    </tr>
    </tbody>
    </table>
  </li>


<li>
<table class="F_Table">
<tbody>
  <tr>
    <td class="F_Lcol"><fmt:message key="reg.fname"/>
    </td>
    <td class="F_Req">*</td>
    <td class="F_Val"> <input class="first-name" type="text" size="30" name="first-name" required autocomplete="off" pattern="[A-zА-я]+"></td>
    <td class="F_Erms"></td>
    </tr>
    </tbody>
    </table>
 </li>


<li>
<table class="F_Table">
<tbody>
<tr>
    
     <td class="F_Lcol"><fmt:message key="reg.birthday"/>
     </td>
    <td class="F_Req">*</td>
    <td class="F_Val"> 

    <table class="F_InnerTab">
    <tr>
    <td class="F_InnerData" style="width:60px;"><fmt:message key="reg.year"/>
    </td>
    <td class="F_InnerData" style="width:100px;"><fmt:message key="reg.month"/>
    </td>
    <td class="F_InnerData" style="width:70px;"><fmt:message key="reg.day"/>
    </td>
    </tr>
    <tr><td><select name="year-birth" style="width:70px;">
        <option value="-1">&nbsp;</option>
        <option value="1999">1999</option><option value="1998">1998</option><option value="1997">1997</option><option value="1996">1996</option><option value="1995">1995</option><option value="1994">1994</option><option value="1993">1993</option><option value="1992">1992</option><option value="1991">1991</option><option value="1990">1990</option><option value="1989">1989</option><option value="1988">1988</option><option value="1987">1987</option><option value="1986">1986</option><option value="1985">1985</option><option value="1984">1984</option><option value="1983">1983</option><option value="1982">1982</option><option value="1981">1981</option><option value="1980">1980</option><option value="1979">1979</option><option value="1978">1978</option><option value="1977">1977</option><option value="1976">1976</option><option value="1975">1975</option><option value="1974">1974</option><option value="1973">1973</option><option value="1972">1972</option><option value="1971">1971</option><option value="1970">1970</option><option value="1969">1969</option><option value="1968">1968</option><option value="1967">1967</option><option value="1966">1966</option><option value="1965">1965</option><option value="1964">1964</option><option value="1963">1963</option><option value="1962">1962</option><option value="1961">1961</option><option value="1960">1960</option><option value="1959">1959</option><option value="1958">1958</option><option value="1957">1957</option><option value="1956">1956</option><option value="1955">1955</option><option value="1954">1954</option><option value="1953">1953</option><option value="1952">1952</option><option value="1951">1951</option><option value="1950">1950</option><option value="1949">1949</option><option value="1948">1948</option><option value="1947">1947</option><option value="1946">1946</option><option value="1945">1945</option><option value="1944">1944</option><option value="1943">1943</option><option value="1942">1942</option><option value="1941">1941</option><option value="1940">1940</option><option value="1939">1939</option><option value="1938">1938</option><option value="1937">1937</option><option value="1936">1936</option><option value="1935">1935</option><option value="1934">1934</option><option value="1933">1933</option><option value="1932">1932</option><option value="1931">1931</option><option value="1930">1930</option><option value="1929">1929</option><option value="1928">1928</option><option value="1927">1927</option><option value="1926">1926</option><option value="1925">1925</option><option value="1924">1924</option><option value="1923">1923</option><option value="1922">1922</option><option value="1921">1921</option><option value="1920">1920</option><option value="1919">1919</option>
        <option value="1918">1918</option><option value="1917">1917</option><option value="1916">1916</option><option value="1915">1915</option><option value="1914">1914</option><option value="1913">1913</option>
        <option value="1912">1912</option><option value="1911">1911</option><option value="1910">1910</option>
        <option value="1909">1909</option><option value="1908">1908</option><option value="1907">1907</option>
        <option value="1906">1906</option><option value="1905">1905</option><option value="1904">1904</option>
        <option value="1903">1903</option><option value="1902">1902</option><option value="1901">1901</option>
        <option value="1900">1900</option><option value="1899">1899</option><option value="1898">1898</option>
        <option value="1897">1897</option>

    </select></td>


        <td><select name="mon-birth" style="width:100px;">
            <option value="00">&nbsp;</option><option value="01"><fmt:message key="reg.m1"/>
        </option>
            <option value="02"><fmt:message key="reg.m2"/>
            </option> <option value="03"><fmt:message key="reg.m3"/>
        </option>
            <option value="04"><fmt:message key="reg.m4"/>
            </option> <option value="05"><fmt:message key="reg.m5"/>
        </option>
            <option value="06"><fmt:message key="reg.m6"/>
            </option><option value="07"><fmt:message key="reg.m7"/>
        </option>
            <option value="08"><fmt:message key="reg.m8"/>
            </option> <option value="09"><fmt:message key="reg.m9"/>
        </option>
            <option value="10"><fmt:message key="reg.m10"/>
            </option><option value="11"><fmt:message key="reg.m11"/>
        </option>
            <option value="12"><fmt:message key="reg.m12"/>
            </option>

        </select></td>
<td><select class="birthday" name="day" style="width:60px;"></select</td>
          <option value="00">&nbsp;</option>
          <option value="01">1</option>
          <option value="02">2</option>
          <option value="03">3</option>
          <option value="04">4 </option>
          <option value="05">5</option>
          <option value="06">6</option>
          <option value="07">7</option>
          <option value="08">8</option>
          <option value="09">9</option>
          <option value="10">10</option>
          <option value="11">11</option>
          <option value="12">12</option>
          <option value="13">13</option>
          <option value="14">14</option>
          <option value="15">15</option>
          <option value="16">16 </option>
          <option value="17">17</option>
          <option value="18">18</option>
          <option value="19">19</option>
          <option value="20">20</option>
          <option value="21">21</option>
          <option value="22">22</option>
          <option value="23">23</option>
          <option value="24">24</option>
          <option value="25">25</option>
          <option value="26">26</option>
          <option value="27">27</option>
          <option value="28">28</option>
          <option value="29">29</option>
          <option value="30">30</option>
          <option value="31">31</option>

        <input class="date" type="hidden" name="birthday" required  id="id_date"/>


        <script type="text/javascript">

            var $selects = $('select'), $dateInput = $("#id_date");

            $selects.change(function(){

                $dateInput.val( $selects.map(function(){return $(this).val()}).get().join("-") );

            }).triggerHandler("change");

        </script>

    </tr>
    </table>
   
  </tr>
    </tbody>
    </table>
    </li>

<li>
<table class="F_Table">
<tbody>
  <tr>
    <td class="F_Lcol"><fmt:message key="reg.mail"/>
    </td>
    <td class="F_Req">*</td>
    <td class="F_Val"> <input class="email" type="email"  name="email" size="30" placeholder="example@your.mail"  required autocomplete="off"  pattern="[A-z0-9_\.-]+@[a-z]+\.[a-z]{2,4}"></td>

    </tr>
    </tbody>
    </table>
    </li>


<li>
<table class="F_Table">
<tbody>
  <tr>
    <td class="F_Lcol"></td>
    <td class="F_Req"></td>
    <td class="F_Val"> <div id="testform">
      <input type="hidden" id="default-id" size="30"   >
           </div> </td>
    <td class="F_Erms"></td>
    </tr>
    </tbody>
    </table>
    </li>


<li>
<table class="F_Table">
<tbody>
  <tr>
    <td class="F_Lcol"><fmt:message key="reg.phone"/>
    </td>
    <td class="F_Req">*</td>
    <td class="F_Val"> <input class="phone" name="phone" type="phone" size="30" placeholder="+375"  required autocomplete="off" pattern="\(?\+[0-9]{1,3}\)? ?-?[0-9]{1,3} ?-?[0-9]{3,5} ?-?[0-9]{4}( ?-?[0-9]{3})? ?(\w{1,10}\s?\d{1,6})?" minlength="7" maxlength="13" ></td>
    <td class="F_Erms"></td>
    </tr>
    </tbody>
    </table>
    </li>

<li>
<table class="F_Table">
<tbody>
  <tr>
    <td class="F_Lcol"><fmt:message key="reg.dtype"/>
    </td>
    <td class="F_Req">*</td>
    <td class="F_Val"> <input class="doc-type" name="doc-type" required autocomplete="off" type="text" size="30"></td>
    <td class="F_Erms"></td>
    </tr>
    </tbody>
    </table>
    </li>

<li>
    <table class="F_Table">
        <tbody>
        <tr>
            <td class="F_Lcol"><fmt:message key="reg.dnumb"/>
            </td>
            <td class="F_Req">*</td>
            <td class="F_Val"> <input class="doc-numb" name="doc-numb" required autocomplete="off" type="text" size="30"></td>
            <td class="F_Erms"></td>
        </tr>
        </tbody>
    </table>
</li>


    <li>
        <table class="F_Table">
            <tbody>
            <tr>
                <td class="F_Lcol"> </td>
                <td class="F_Req"></td>
                <td class="F_Val"> <select class="currency" name="currency" required>
                    <option value="RUB">RUB</option>
                    <option value="BYN">BYN</option>
                    <option value="USD">USD</option>
                </select></td>
                <td class="F_Erms"></td>
            </tr>
            </tbody>
        </table>
    </li>


<li>
<table class="F_Table">
<tbody>
  <tr>
    <td class="F_Lcol"><fmt:message key="reg.pass"/>
    </td>
    <td class="F_Req">*</td>
    <td class="F_Val"> <input   required autocomplete="off"  pattern="[A-z0-9_-]{6,18}"  type="password" size="30" name="password" title="Choose your password. Six or more characters" onKeyUp="passValid('form','pass','pass12','submit'),isRavno('form','pass','confirm','pass22','submit')">
     <span id="pass11" ><span id="pass12" ></span></span><br /></td>
    <td class="F_Erms"></td>
    </tr>
    </tbody>
    </table>
    </li>

<li>
<table class="F_Table">
<tbody>
  <tr>
    <td class="F_Lcol"><fmt:message key="reg.confirm"/>
    </td>
    <td class="F_Req">*</td>
    <td class="F_Val"> <input  required autocomplete="off" type="password" size="30" pattern="[A-z0-9_-]{6,18}"  name="confirm"  title="Choose your password. Six or more characters" onKeyUp="isRavno('form','password','confirm','pass22','submit')">   <span id="pass22" ></span><br /></td>
    <td class="F_Erms"></td>
    </tr>
    </tbody>
    </table>
    </li>


<li>
<table class="F_Table">
<tbody>
  <tr>
    <td class="F_Lcol"></td>
    <td class="F_Req"><input type="checkbox" name="option" required/></td>
    <td class="F_Val"> <label><fmt:message key="reg.labelage"/>
    </label>
        
</td>
    <td class="F_Erms"></td>
    </tr>
    </tbody>
    </table>
    </li>

<p><fmt:message key="reg.data"/>
</p>

</ul>
    <div id="submit">
        <input type="submit" class="but_new_big" value="Register">
    </div>
    </form>

</div>




<button class="but_new_big" name="back" onclick="history.back()" ><fmt:message key="reg.back"/>
</button>

</div>
</body>
</html>