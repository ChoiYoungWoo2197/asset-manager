<template>
  <div class="wrapper">
    <!-- Navbar -->
    <nav class="main-header navbar navbar-expand-md navbar-light navbar-white">
      <div class="container">
        <router-link :to="{ name : 'Index'}" class="navbar-brand">
          <img src="@/assets/img/cmt_logo.svg" style="width: 200px; height: 26px;" title="">
        </router-link>

        <button class="navbar-toggler order-1" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse order-3" id="navbarCollapse">
          <!-- Left navbar links -->
          <ul class="navbar-nav">
            <li class="nav-item dropdown">
              <a id="dropdownSubMenu1" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle">기초정보</a>
              <ul aria-labelledby="dropdownSubMenu1" class="dropdown-menu border-0 shadow">
                <li>
                  <router-link :to="{ name : 'Authority'}" class="dropdown-item">권한정보관리</router-link>
                </li>
                <li>
                  <router-link :to="{ name : 'Organization'}" class="dropdown-item">조직도 정보관리</router-link>
                </li>
                <li>
                  <router-link :to="{ name : 'Category'}" class="dropdown-item">카테고리 정보관리</router-link>
                </li>
                <li>
                  <router-link :to="{ name : 'PartnerCompany'}" class="dropdown-item">업체 정보관리</router-link>
                </li>

              </ul>
            </li>
            <li class="nav-item dropdown">
              <a id="dropdownSubMenu2" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link dropdown-toggle">자산관리</a>
              <ul aria-labelledby="dropdownSubMenu2" class="dropdown-menu border-0 shadow">
                <li>
                  <router-link :to="{ name : 'Asset'}" class="dropdown-item">자산 정보관리</router-link>
                </li>
                <li>
                  <a href="./views/assetStatus.html" class="dropdown-item">
                    자산할당현황 정보관리
                  </a>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <!-- /.navbar -->

    <!-- Content Wrapper. Contains page content -->
    <router-view />
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <footer class="main-footer">
      <div class="container">
        <address>
          <small>(04798) 서울시 성동구 성수이로22길 37, 803호(성수동2가, 아크밸리)</small>
          <span class="ft_line">|</span><small>Tel : 02-6011-1377</small>
          <span class="ft_line">|</span><small>Fax : 02-463-1376</small>
          <span class="ft_line">|</span><small>E-mail: adm@cmtinfo.co.kr</small>
          <span class="ft_line">|</span><small>사업자번호 : 215-87-14581<br></small>
          <small>COPYRIGHT © CMT정보통신 ALL RIGHTS RESERVED.</small>
        </address>
      </div>
    </footer>
  </div>


</template>

<style>
@import url('https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback');

</style>
