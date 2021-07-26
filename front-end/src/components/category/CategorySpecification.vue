<template>
  <div>
    <template v-if="category === null">
      <div>
        <i class="fas fa-info-circle pr-1"></i>정보가 없습니다.
      </div>
    </template>
    <template v-else>
      <div class="card-body">
        <form class="member">
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
          </div>
        </form>
        <div class="form-group">
          <div class="input-group">
            <input id="search" type="search" class="form-control" placeholder="규격명" v-model=search>
            <div class="input-group-append">
              <button type="submit" class="btn btn-primary" @click.prevent="clickSearchBtn">
                <i class="fa fa-search"></i>
              </button>
            </div>
          </div>
        </div>
        <div class="card-body table-responsive p-0 table-hover">
          <table class="table table-head-fixed text-nowrap">
            <thead>
            <tr>
              <th>#</th>
              <!--                                            <th><input type="checkbox"></th>-->
              <th>카테고리번호</th>
              <th>카테고리명</th>
              <th>규격명</th>
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
              <td v-text="data.name" class=""></td>
              <td v-text="data.name" class=""></td>
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
    </template>

    <!-- 모달 컴포넌트  -->
<!--    <MemberCreate ref="memberCreate" :p-department="pDepartment" @updateData="handleUpdateData" style="display: none"></MemberCreate>
    <MemberUpdate ref="memberUpdate" :p-department="pDepartment" @updateData="handleUpdateData" style="display: none"></MemberUpdate>-->
    <!-- /모달 컴포넌트  -->
  </div>
</template>


<script>
// import $ from "jquery";
// import axios from "axios";

export default {
  name: "CategorySpecification",
  components: {
  },
  props: {
    pCategory: Object,
  },
  data() {
    return {
      category : null,
      responseData : null,
      data : null,
      pageable : null,
      currentPage : 0, //현재 페이지
      perPageNum : 10, //게시글의 수
      displayPageNum : 10, // 페이지 번호의 수
      pages : [],
      pageInfo : null,
      search : '',
    }
  },
  computed: {
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
    pCategory() {
      this.currentPage = 0;
      this.category = this.pCategory;
/*      this.findAuthoriyAndDepartment();
      this.searchData(false);*/
    },
    currentPage() {
      this.searchData();
    },
  },
  mounted() {
    this.category = this.pCategory;
    // this.findAuthoriyAndDepartment();
  },
  methods: {
    searchData() {

    },
  }
}
</script>

<style scoped>

</style>