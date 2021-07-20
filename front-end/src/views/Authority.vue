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
                          <label for="test8">사용유무</label>
                          <select class="form-control " style="width: 100%;" id="test8">
                            <option>해당없음</option>
                            <option>비활성화</option>
                            <option>활성화</option>
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
                      <input type="search" class="form-control " placeholder="Type your keywords here" value="키워드">
                      <div class="input-group-append">
                        <button type="submit" class="btn btn-primary">
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
                      <tr v-for="(data, index) in getDatas"  :key="index+1" @click="clickTrTag(data)">
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
                <h4 class="card-title">Showing 1 to 4 of 12 entries</h4>
                <ul class="pagination pagination-sm m-0 float-right">
                  <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
                  <li class="page-item"><a class="page-link" href="#">1</a></li>
                  <li class="page-item"><a class="page-link" href="#">2</a></li>
                  <li class="page-item"><a class="page-link" href="#">3</a></li>
                  <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
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
      data : null,
    }
  },
  computed : {
    getDatas() {
      return this.data;
    }
  },
  mounted() {
    document.body.classList.remove('login-page');
    document.body.classList.add('layout-top-nav');
    this.loadData();
  },
  methods: {
    loadData() {
      const vm = this;
      axios.get('http://localhost:8080/api/authoritys')
          .then(response => {
            console.log(response);
            if(response.status === 200) {
              vm.data = [];
              vm.data = response.data;
              console.log(vm.data);
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
      this.loadData();
    },
    clickTrTag(data){
      // console.log('tr data : ', data);
      this.$refs.authorityUpdate.setData(data);
      this.showUpdateModal();
    }
  }
}
</script>

<style scoped>

</style>