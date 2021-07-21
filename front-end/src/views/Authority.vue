<template>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div class="content-header">
      <div class="container">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h4 class="m-0"> 권한정보관리 <small></small></h4>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">기초정보</a></li>
              <li class="breadcrumb-item active">권한정보관리</li>
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
              <button class="create btn btn-secondary btn-sm" @click="showCreateModal">
                <i class="far fa-plus-square pr-1"></i>등록
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
                          <label for="useYn">사용유무</label>
                          <select class="form-control " style="width: 100%;" id="useYn">
                            <option value="">해당없음</option>
                            <option value="false">비활성화</option>
                            <option value="true">활성화</option>
                          </select>
                        </div>
                      </div>
                      <div class="col-3">
                      </div>
                      <div class="col-3">
                      </div>
                      <div class="col-3">
                      </div>
                    </div>
                  </form>
                  <div class="form-group">
                    <div class="input-group">
                      <input id="search" type="search" class="form-control" placeholder="키워드">
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
                        <th>권한명</th>
                        <th>권한코드</th>
                        <th>비고</th>
                        <th>사용유무</th>
                        <th>등록일자</th>
                        <th>수정일자</th>
                        <th>등록자</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr v-for="(data, index) in getDatas"  :key="index" @click="clickTrTag(data)">
                        <td v-text="index+1" class=""></td>
                        <td v-text="data.name" class=""></td>
                        <td v-text="data.code" class=""></td>
                        <td v-text="data.remark" class=""></td>
                        <td v-text="data.useYn === true ? '활성화' : '비활성화'" class=""></td>
                        <td v-text="data.registedDateAt" class=""></td>
                        <td v-text="data.updatedDateAt" class=""></td>
                        <td v-text="data.register" class=""></td>
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

    <!-- 모달 컴포넌트  -->
    <AuthorityCreate ref="authorityCreate" @updateData="handleUpdateData" style="display: none"></AuthorityCreate>
    <AuthorityUpdate ref="authorityUpdate" @updateData="handleUpdateData" style="display: none"></AuthorityUpdate>
    <!-- /모달 컴포넌트  -->
  </div>
  <!-- /.content-wrapper -->
</template>

<script>
String.format = function() {
  let args = arguments;
  return args[0].replace(/{(\d+)}/g, function(match, num) {
    num = Number(num) + 1;
    return typeof(args[num]) != undefined ? args[num] : match;
  });
}




import $ from "jquery";
import axios from 'axios';
import AuthorityCreate from '@/components/authority/authority-create.vue';
import AuthorityUpdate from '@/components/authority/authority-update.vue';

export default {
  name: "Authority",
  components: {
    AuthorityCreate,
    AuthorityUpdate,
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
    }
  },
  computed : {
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
  mounted() {
    document.body.classList.remove('login-page');
    document.body.classList.add('layout-top-nav');
    this.searchData();
  },
  watch: {
    currentPage() {
      this.searchData();
    }
  },
  methods: {
    searchData() {
      const vm = this;
      axios.get('http://localhost:8080/api/authoritys', {
        params: {
          page : vm.currentPage,
          size : vm.perPageNum,
          useYn:$('#useYn').val(),
          remark: $('#search').val()
        }
      }).then(response => {
        // console.log(response);
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
    showCreateModal() {
      this.$refs.authorityCreate.clearData();
      $('#authority-create-modal').modal("show");
    },
    showUpdateModal() {
      $('#authority-update-modal').modal("show");
    },
    handleUpdateData() {
      this.currentPage = 0;
      this.searchData();
    },
    clickTrTag(data){
      this.$refs.authorityUpdate.setData(data);
      this.showUpdateModal();
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

    }

  }
}
</script>

<style scoped>

</style>