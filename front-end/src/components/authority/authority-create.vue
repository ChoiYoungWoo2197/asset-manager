<template>
  <!-- Modal -->
  <div class="modal fade" id="authority-create-modal" tabindex="-1" aria-labelledby="authority-create-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header ">
          <h2 class="tit_step">권한등록</h2>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <div class="row">
            <div class="col-lg">
              <div class="card card-primary card-outline">
                <div class="card-body">
                  <form>
                    <div class="form-group">
                      <label for="name">권한명<span class="text-danger">*</span></label>
                      <input type="text" class="form-control" id="name" placeholder="">
                    </div>
                    <div class="form-group">
                      <label for="code">권한코드<span class="text-danger">*</span></label>
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
              </div>
            </div>
          </div>

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
import axios from 'axios';

export default {
  name: "authority-create",
  data() {
    return {
      isExistCode : null,
      useYn : true,
    }
  },
  methods: {
    clearData() {
      $( 'input#name' ).val("");
      $( 'input#code' ).val("");
      $( 'textarea#remark' ).val("");
      $('button#createBtn').addClass('btn-primary');
      $('button#createBtn').addClass('btn-secondary');
      this.isExistCode = null;
      this.useYn = true;
    },
    createBtnClick() {
      const vm = this;
      if($( 'input#name' ).val() ==="" || $( 'input#code' ).val() === "" || this.isExistCode !== false) {
        if($( 'input#name' ).val() === '') {
          alert("이름을 입력해주세요.");
          return false;
        } else if($( 'input#code' ).val() === "" ) {
          alert("코드를 입력해주세요.");
          return false;
        } else if( this.isExistCode !== false ) {
          //제일 마지막에 추가해주자.
          alert('코드 중복체크를 해주세요.');
          return false;
        }
      }

      // console.log($( 'input#name' ).val(), $( 'input#code' ).val(),$( 'textarea#remark' ).val(), this.useYn);

      axios.post('http://localhost:8080/api/authoritys', {
        name : $( 'input#name' ).val(),
        code : $( 'input#code' ).val(),
        remark : $( 'textarea#remark' ).val(),
        useYn : this.useYn,
      }).then(response => {
        console.log(response);
        if(response.status === 200) {
          vm.$emit("updateData", response.data);
          $('#authority-create-modal').modal("hide");
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
      if($( 'input#code' ).val() === "") {
        alert("코드를 입력해주세요.");
        return false;
      }

      axios.get('http://localhost:8080/api/authoritys/' + $( 'input#code' ).val() + '/exists')
      .then(response => {
        if(response.status === 200) {
          if(response.data === true) {
            vm.isExistCode = true;
            alert("중복된 코드입니다.");
          } else {
            vm.isExistCode = false;
            $('button#createBtn').removeClass('btn-secondary');
            $('button#createBtn').addClass('btn-primary');
            alert("사용 가능한 코드입니다.");
          }
        }
      }).catch(e => {
        alert(e);
      })
    }
  },
}
</script>

<style scoped>

</style>