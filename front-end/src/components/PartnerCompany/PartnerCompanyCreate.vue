<template>
  <!-- Modal -->
  <div class="modal fade" id="partner-company-create-modal" tabindex="-1" aria-labelledby="partner-company-create-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header ">
          <h2 class="tit_step">업체등록</h2>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form>
            <div class="form-group">
              <label for="name">업체명<span class="text-danger">*</span></label>
              <input type="text" class="form-control" id="name" placeholder="">
            </div>
            <div class="form-group">
              <label for="code">업체코드<span class="text-danger">*</span></label>
              <div class="form-group row">
                <div class="col-sm-10">
                  <input type="text" class="form-control " id="code" placeholder="">
                </div>
                <div class="col-sm">
                  <input type="button" class="form-control btn btn-primary btn-sm" value="중복체크" @click="clickCodeCheckBtn">
                </div>
              </div>
            </div>
            <div class="form-group">
              <label for="type">구분<span class="text-danger">*</span></label>
              <select class="form-control " style="width: 100%;" id="type">
                <option v-for="(type, index) in types"
                        :key="index"
                        :value="type.code"
                        :selected="index === 0">
                  {{ type.name }}
                </option>
              </select>
            </div>
            <div class="form-group">
              <label for="companyPhone">전화번호<span class="text-danger"></span></label>
              <input type="text" class="form-control " id="companyPhone" placeholder="">
            </div>
            <div class="form-group">
              <label for="remark">비고<code></code></label>
              <textarea class="form-control" id="remark"></textarea>
            </div>
            <div class="form-group">
              <div class="custom-control custom-radio d-inline pr-1">
                <input class="custom-control-input" type="radio" id="useYnFalse" name="useYnRadio" @click="clickRadioBtn(false)">
                <label for="useYnFalse" class="custom-control-label">비활성화</label>
              </div>
              <div class="custom-control custom-radio d-inline">
                <input class="custom-control-input" type="radio" id="useYnTrue" name="useYnRadio" checked="" @click="clickRadioBtn(true)">
                <label for="useYnTrue" class="custom-control-label">활성화</label>
              </div>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">  <i class="far fa-window-close pr-1"></i>취소</button>
          <button id="createBtn" type="button" class="btn btn-secondary" @click="createBtnClick"> <i class="far fa-edit pr-1"></i>등록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import axios from "axios";

export default {
  name: "PartnerCompanyCreate",
  data() {
    return {
      isExistCode : null,
      useYn : true,
      types: [
        {code : 'buy', name : '구매'},
        {code : 'rental', name : '렌탈'},
      ]
    }
  },
  methods: {
    clearData() {
      $( '#partner-company-create-modal input#name' ).val("");
      $( '#partner-company-create-modal input#code' ).val("");
      $( '#partner-company-create-modal select#type' ).val("buy");
      $( '#partner-company-create-modal select#companyPhone' ).val("");
      $( '#partner-company-create-modal textarea#remark' ).val("");
      $('button#createBtn').addClass('btn-primary');
      $('button#createBtn').addClass('btn-secondary');
      this.isExistCode = null;
      this.useYn = true;
    },
    createBtnClick() {
      const vm = this;
      if($( '#partner-company-create-modal input#name' ).val() ==="" || $( '#partner-company-create-modal input#code' ).val() === "" || this.isExistCode !== false) {
        if($( '#partner-company-create-modal input#name' ).val() === '') {
          alert("업체명을 입력해주세요.");
          return false;
        } else if($( '#partner-company-create-modal input#code' ).val() === "" ) {
          alert("코드를 입력해주세요.");
          return false;
        } else if( this.isExistCode !== false ) {
          //제일 마지막에 추가해주자.
          alert('코드 중복체크를 해주세요.');
          return false;
        }
      }

      // console.log($( 'input#name' ).val(), $( 'input#code' ).val(),$( 'textarea#remark' ).val(), this.useYn);

      axios.post('http://localhost:8080/api/partner-companys', {
        name : $( '#partner-company-create-modal input#name' ).val(),
        code : $( '#partner-company-create-modal input#code' ).val(),
        type : $( '#partner-company-create-modal select#type' ).val(),
        companyPhone : $( '#partner-company-create-modal input#companyPhone' ).val(),
        remark : $( '#partner-company-create-modal textarea#remark' ).val(),
        useYn : this.useYn,
        register : "1"
      }).then(response => {
        console.log(response);
        if(response.status === 200) {
          vm.$emit("updateData", response.data);
          $('#partner-company-create-modal').modal("hide");
        }
      }).catch(e => {
        alert(e);
      })
    },
    clickRadioBtn(flag){
      this.useYn = flag;
    },
    clickCodeCheckBtn() {
      const vm = this;
      if($( '#partner-company-create-modal input#code' ).val() === "") {
        alert("코드를 입력해주세요.");
        return false;
      }

      axios.get('http://localhost:8080/api/partner-companys/' + $( '#partner-company-create-modal input#code' ).val() + '/exists')
          .then(response => {
            if(response.status === 200) {
              if(response.data === true) {
                vm.isExistCode = true;
                alert("중복된 코드입니다.");
              } else {
                vm.isExistCode = false;
                $('#partner-company-create-modal button#createBtn').removeClass('btn-secondary');
                $('#partner-company-create-modal button#createBtn').addClass('btn-primary');
                alert("사용 가능한 코드입니다.");
              }
            }
          }).catch(e => {
        alert(e);
      })
    }
  }
}
</script>

<style scoped>

</style>