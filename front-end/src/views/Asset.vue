<template>
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h4 class="m-0"> 자산정보관리 <small></small></h4>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">자산관리</a></li>
              <li class="breadcrumb-item active">자산정보관리</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <div class="content">
      <div class="container">
        <div class="mb-2">
          <div class="text-right">
            <div>
              <button class="create btn btn-secondary btn-sm mr-1" @click="showCreateModal">
                <i class="far fa-plus-square pr-1"></i>등록
              </button>
              <button class="file-upload btn btn-secondary btn-sm mr-1">
                <i class="fas fa-file-download pr-1"></i>파일업로드
              </button>
              <button class="btn btn-secondary btn-sm">
                <i class="fas fa-file-export pr-1"></i>파일다운로드
              </button>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-lg">
            <div class="card card-primary card-outline">
              <div class="card-header">
                <div class="col-lg">
                  <form>
                    <div class="form-row">
                      <div class="col-3">
                        <div class="form-group">
                          <label for="typeView">구분</label>
                          <select class="form-control " style="width: 100%;" id="typeView">
                            <option value="">해당없음</option>
                            <option value="buy">구매</option>
                            <option value="rental">렌탈</option>
                          </select>
                        </div>
                        <div class="form-group">
                          <label for="partnerCompanysView">업체명</label>
                          <select class="form-control" style="width: 100%;" id="partnerCompanysView">
                            <option value="">해당없음</option>
                          </select>
                        </div>
                      </div>
                      <div class="col-3">
                        <div class="form-group">
                          <label for="categorysView">카테고리</label>
                          <select class="form-control" style="width: 100%;" id="categorysView">
                            <option value="">해당없음</option>
                          </select>
                        </div>
                        <div class="form-group">
                          <label for="contractDatesView">계약기간</label>
                          <input class="form-control" type="text" id="contractDatesView" placeholder="" value="">
                        </div>
                      </div>
                      <div class="col-3">
                        <div class="form-group">
                          <label for="membersView">담당자</label>
                          <select class="form-control" style="width: 100%;" id="membersView">
                            <option value="">해당없음</option>
                          </select>
                        </div>
                        <div class="form-group">
                          <label for="receiveDateView">수령일자</label>
                          <input class="form-control" type="text" id="receiveDateView"  placeholder="" value="">
                        </div>

                      </div>
                      <div class="col-3">
                        <div class="form-group">
                          <label for="departmentsView">부서</label>
                          <select class="form-control" style="width: 100%;" id="departmentsView">
                            <option value="">해당없음</option>
                          </select>
                        </div>
                        <div class="form-group">
                          <label for="useYnView">사용유무</label>
                          <select class="form-control " style="width: 100%;" id="useYnView">
                            <option value="">해당없음</option>
                            <option value="false">비활성화</option>
                            <option value="true">활성화</option>
                          </select>
                        </div>
                      </div>
                    </div>
                  </form>
                  <div class="form-group">
                    <div class="input-group">
                      <input type="search" class="form-control " placeholder="자산명" v-model="search">
                      <div class="input-group-append">
                        <button type="submit" class="btn btn-primary" @click.prevent="clickSearchBtn">
                          <i class="fa fa-search"></i>
                        </button>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="card-body">
                <div class="card-body table-responsive p-0 table-hover" style="height: 500px;">
                  <table class="table table-head-fixed text-nowrap">
                    <thead>
                    <tr>
                      <th>#</th>
                      <!--                                            <th><input type="checkbox"></th>-->
                      <th>자산명</th>
                      <th>자산코드</th>
                      <th>카테고리명</th>
                      <th >수량</th>
                      <th>가격</th>
                      <th>구매일자</th>
                      <th>수령일자</th>
                      <th>담당자</th>
                      <th>담당부서</th>
                      <th>구분</th>
                      <th>업체명</th>
                      <th>계약시작일자</th>
                      <th>계약종료일자</th>
                      <th>비고</th>
                      <th>사용유무</th>
                      <th>등록자</th>
                      <th>등록일자</th>
                      <th>수정일자</th>
                      <th>렌탈내역</th>
                      <th>변경내역</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr v-for="(data, index) in getDatas"  :key="index" @click="clickTrTag(data)">
                      <td v-text="index+1" class=""></td>
                      <td v-text="data.name" class=""></td>
                      <td v-text="data.code" class=""></td>
                      <td v-text="data.categoryName" class=""></td>
                      <td v-text="data.count" class=""></td>
                      <td v-text="data.price" class=""></td>
                      <td v-text="data.buyDateAt" class=""></td>
                      <td v-text="data.receivedDateAt" class=""></td>
                      <td v-text="data.memberName" class=""></td>
                      <td v-text="data.departmentName" class=""></td>
                      <td v-text="data.type === 'buy' ? '구매' : '렌탈'" class=""></td>
                      <td v-text="data.partnerCompanyName" class=""></td>
                      <td v-text="data.contractDateAt" class=""></td>
                      <td v-text="data.expireDateAt" class=""></td>
                      <td v-text="data.remark" class=""></td>
                      <td v-text="data.useYn === true ? '활성화' : '비활성화'" class=""></td>
                      <td v-text="data.register" class=""></td>
                      <td v-text="data.registedDateAt" class=""></td>
                      <td v-text="data.updatedDateAt" class=""></td>
                      <td>
                        <button class="btn btn-info btn-sm">렌탈내역</button>
                      </td>
                      <td>
                        <button class="btn btn-info btn-sm">변경내역</button>
                      </td>
                    </tr>
                    </tbody>
                  </table>
                </div>
              </div>
              <div class="card-footer clearfix">
                <h4 class="card-title">{{pageInfo}}</h4>
                <ul class="pagination pagination-sm m-0 float-right">
                  <li class="page-item" :class="{disabled: isFirstPageGroup}">
                    <a class="page-link" href="#" aria-label="Previous" @click="clickPreviousPageBtn">&laquo;</a>
                  </li>
                  <li class="page-item" v-for="page in pages" :key="page" :class="{active: page-1 === currentPage}">
                    <a class="page-link" v-text="page" href="#" @click="clickPageBtn(page-1)"></a>
                  </li>
                  <li class="page-item" :class="{disabled: isLastPageGroup}">
                    <a class="page-link" href="#" aria-label="Next" @click="clickNextPageBtn">&raquo;</a>
                  </li>
                </ul>
              </div>
            </div><!-- /.card -->
          </div>
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content -->


    <!--  모달 컴포넌트  -->
    <AssetCreate ref="assetCreate" @updateData="handleUpdateData" style="display : none"></AssetCreate>
    <AssetUpdate ref="assetUpdate" @updateData="handleUpdateData" style="display : none"></AssetUpdate>

    <!--  /모달 컴포넌트  -->

  </div>
</template>

<script>
import AssetCreate from "@/components/asset/AssetCreate.vue";
import AssetUpdate from "@/components/asset/AssetUpdate.vue";
import $ from "jquery";
import axios from "axios";
import moment from "moment";

export default {
  name: "Asset",
  components: {
    AssetCreate,
    AssetUpdate,
  },
  data() {
    return {
      responseData : null,
      data : null,
      pageable : null,
      currentPage : 0, //현재 페이지
      perPageNum : 10, //게시글의 수
      displayPageNum : 10, // 페이지 번호의 수
      pages : [],
      pageInfo : null,
      search : '',
      categorys : [],
      members : [],
      departments : [],
      partnerCompanys : [],
    }
  },
  computed: {
    getDatas() {
      return this.data;
    },
    isFirstPageGroup() {
      if(this.responseData === null) return false;
      return this.responseData.data.first;
    },
    isLastPageGroup() {
      if(this.responseData === null) return false;
      return this.responseData.data.last;
    },
  },
  watch: {
    currentPage() {
      this.searchData();
    }
  },
  mounted() {
    document.body.classList.remove('login-page');
    document.body.classList.add('layout-top-nav');
    this.findAll();
    this.initContractDatePicker();
    this.initReceiveDatePicker();
    this.searchData();
  },
  methods: {
    searchData() {
      const vm = this;
      axios.get('http://localhost:8080/api/assets', {
        params: {
          page : vm.currentPage,
          size : vm.perPageNum,
          useYn:$('#useYnView').val(),
          name: vm.search
        }
      }).then(response => {
        console.log(response);
        if(response.status === 200) {
          vm.responseData = response;
          vm.data = response.data.content;
          vm.pageable = response.data.pageable;
          vm.getStartAndEndPage();
          // console.log(vm.responseData, vm.data, vm.pageable);
        }
      }).catch(e => {
        alert(e);
      })
    },
    handleUpdateData() {
      this.currentPage = 0;
      this.searchData();
    },
    showCreateModal() {
      this.$refs.assetCreate.clearData();
      $('#asset-create-modal').modal("show");
    },
    clickTrTag(data) {
      console.log(data);
    },
    getStartAndEndPage() {
      this.pages = [];
      let endPage = Math.ceil((this.pageable.pageNumber+1) / this.displayPageNum) * this.displayPageNum;
      let startPage = (endPage - this.displayPageNum) + 1;
      let tempEndPage = Math.ceil(this.responseData.data.totalElements / this.perPageNum);

      if(endPage > tempEndPage) {
        endPage = tempEndPage;
      }

      for(var i=startPage; i<=endPage; i++) {
        this.pages.push(i);
      }

      this.pageInfo = String.format("Showing {0} to {1} of {2} entries", (this.pageable.pageNumber * this.perPageNum)+1,
          (this.pageable.pageNumber * this.perPageNum)+this.responseData.data.numberOfElements, this.responseData.data.totalElements);

    },
    clickPreviousPageBtn() {
      this.currentPage = this.pageable.pageNumber === 0 ? 0 : this.pageable.pageNumber-1;

    },
    clickNextPageBtn() {
      let tempEndPage = Math.ceil(this.responseData.data.totalElements / this.perPageNum);
      this.currentPage = this.pageable.pageNumber > tempEndPage ? tempEndPage : this.pageable.pageNumber+1;
      console.log(this.currentPage);
    },
    clickPageBtn(page) {
      this.currentPage = page;
    },
    clickSearchBtn() {
      this.currentPage = 0;
      this.searchData();
    },
    getCategoryInstance() {
      return $("#" + "categorysView");
    },
    getDepartmentInstance() {
      return $("#" + "departmentsView");
    },
    getMemberInstance() {
      return $("#" + "membersView");
    },
    getPartnerCompanyInstance() {
      return $("#" + "partnerCompanysView");
    },
    findCategorys() {
      return axios.get('http://localhost:8080/api/categorys');
    },
    findDepartments() {
      return axios.get('http://localhost:8080/api/departments');
    },
    findMembers() {
      return axios.get('http://localhost:8080/api/members');
    },
    findPartnerCompanys() {
      return axios.get('http://localhost:8080/api/partner-companys');
    },
    findAll() {
      const vm = this;
      axios.all([this.findCategorys(), this.findDepartments(), this.findMembers(),
        this.findPartnerCompanys()])
          .then(axios.spread(function (resCategorys, resDepartments, resMembers, resPartnerCompanys) {
            // console.log(resCategorys, resDepartments, resMembers, resPartnerCompanys, 'dddddddd');

            let categorysNewDatas = [];
            vm.categorys = resCategorys.data.filter(category => {
              return (category.useYn === true);
            });
            vm.categorys.forEach(category => {
              categorysNewDatas.push({
                'id' : category.id ,
                'text' : category.name,
                'defaultSelected' : false,
                'selected' : false
              });
            })
            vm.getCategoryInstance().select2({
              data : categorysNewDatas,
            });

            let departmentsNewDatas = [];
            vm.departments = resDepartments.data.filter(department => {
              return department.useYn === true;
            });
            vm.departments.forEach(department => {
              departmentsNewDatas.push({
                'id' : department.id ,
                'text' : department.name,
                'defaultSelected' : false,
                'selected' : false
              })
            })
            vm.getDepartmentInstance().select2({
              data : departmentsNewDatas,
            });

            let membersNewDatas = [];
            vm.members = resMembers.data.filter(member => {
              return member.useYn === true;
            });
            vm.members.forEach(member => {
              membersNewDatas.push({
                'id' : member.id ,
                'text' : member.name,
                'defaultSelected' : false,
                'selected' : false
              })
            })
            vm.getMemberInstance().select2({
              data : membersNewDatas,
            });

            let partnerCompanyNewDatas = [];
            vm.partnerCompanys = resPartnerCompanys.data.filter(partnerCompany => {
              return partnerCompany.useYn === true;
            })
            vm.partnerCompanys.forEach(partnerCompany => {
              partnerCompanyNewDatas.push({
                'id' : partnerCompany.id ,
                'text' : partnerCompany.name,
                'defaultSelected' : false,
                'selected' : false
              })
            })

            vm.getPartnerCompanyInstance().select2({
              data : partnerCompanyNewDatas,
            });

          })).catch(e => {
        alert(e);
      })
    },
    initContractDatePicker() {
      $('input#contractDatesView').daterangepicker({
        timePicker: true,
        showDropdowns: true,                     // 년월 수동 설정 여부
        autoApply: true,                         // 확인/취소 버튼 사용여부
        startDate: moment().startOf('hour'),
        endDate: moment().startOf('hour').add(32, 'hour'),
        autoUpdateInput: false,
        locale : {
          separator: " ~ ",
          format: 'YYYY-MM-DD',
          applyLabel: "적용",
          cancelLabel: "닫기",
          daysOfWeek: ["일", "월", "화", "수", "목", "금", "토"],
          monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
        },
      }).on('apply.daterangepicker', function(ev, picker) {
        $(this).val(picker.startDate.format('YYYY-MM-DD') + ' ~ ' + picker.endDate.format('YYYY-MM-DD'));
      }).on('cancel.daterangepicker', function(ev, picker) {
        console.log(ev, picker)
        $(this).val('');
      });
    },
    initReceiveDatePicker() {
      $('input#receiveDateView').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        minYear: 1901,
        maxYear: parseInt(moment().format('YYYY'),10),
        autoUpdateInput: false,
        locale : {
          format: 'YYYY-MM-DD',
          applyLabel: "적용",
          cancelLabel: "닫기",
          daysOfWeek: ["일", "월", "화", "수", "목", "금", "토"],
          monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
        }
      }).on('apply.daterangepicker', function(ev, picker) {
        console.log(picker)
        $(this).val(picker.startDate.format('YYYY-MM-DD'));
      }).on('cancel.daterangepicker', function(ev, picker) {
        console.log(ev, picker)
        $(this).val('');
      });
    },
  }
}
</script>

<style scoped>

</style>