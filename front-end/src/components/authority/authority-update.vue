<template>
  <!-- Modal 수정 -->
  <div class="modal fade" id="authority-update-modal" tabindex="-1" aria-labelledby="authority-update-modal-label" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg modal-dialog-scrollable">
      <div class="modal-content ">
        <div class="modal-header">
          <h2 class="tit_step">권한수정</h2>
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
                      <label for="nameEdit">권한명<span class="text-danger">*</span></label>
                      <input type="text" class="form-control" id="nameEdit" placeholder="" :value="name">
                    </div>
                    <div class="form-group">
                      <label for="codeEdit">권한코드<code></code></label>
                      <input type="text" class="form-control " id="codeEdit" placeholder="" readonly :value="code">
                    </div>
                    <div class="form-group">
                      <label for="remarkEdit">비고<code></code></label>
                      <textarea class="form-control" id="remarkEdit" :value="remark"></textarea>
                    </div>
                    <div class="form-group">
                      <div class="custom-control custom-radio d-inline pr-1">
                        <input class="custom-control-input" type="radio" id="useYnFalseEdit" name="useYnRadio" @click="clickRadioBtn(false)">
                        <label for="useYnFalseEdit" class="custom-control-label">비활성화</label>
                      </div>
                      <div class="custom-control custom-radio d-inline">
                        <input class="custom-control-input" type="radio" id="useYnTrueEdit" name="useYnRadio" @click="clickRadioBtn(true)">
                        <label for="useYnTrueEdit" class="custom-control-label">활성화</label>
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
          <button type="button" class="btn btn-primary" @click="updateBtnClick"> <i class="far fa-edit pr-1"></i>수정</button>
          <button type="button" class="btn btn-danger" @click="deleteBtnClick"><i class="far fa-trash-alt pr-1"></i>삭제</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import axios from 'axios';

export default {
  name: "authority-update",
  data() {
    return {
      id : null,
      name : null,
      code : null,
      remark : null,
      useYn : null,
    }
  },
  methods: {
    setData(data) {
      this.id = data.id;
      this.name = data.name;
      this.code = data.code;
      this.remark = data.remark;
      this.useYn = data.useYn;

      if(this.useYn === true) {
        $('#useYnTrueEdit').prop("checked", true);
      } else {
        $('#useYnFalseEdit').prop("checked", true);
      }
    },
    updateBtnClick() {
      const vm = this;

      if($( 'input#nameEdit' ).val() ==="") {
        alert("이름을 입력해주세요.");
        return false;
      }

      axios.put('http://localhost:8080/api/authoritys/' + this.id, {
        name : $( 'input#nameEdit' ).val(),
        code : $( 'input#codeEdit' ).val(),
        remark : $( 'textarea#remarkEdit' ).val(),
        useYn : this.useYn,
      }).then(response => {
        // console.log(response);
        if(response.status === 200) {
          // this.$router.push('Authority');
          vm.$emit("updateData", response.data);
          $('#authority-update-modal').modal("hide");
        }
      }).catch(e => {
        alert(e);
      })
    },
    deleteBtnClick() {
      const vm = this;

      if(!confirm(this.name + "을 삭제하시겠습니까?")) {
        return false;
      }

      axios.delete('http://localhost:8080/api/authoritys/' + this.id)
          .then(response => {
            // console.log(response);
            if(response.status === 200) {
              // this.$router.push('Authority');
              vm.$emit("updateData", response.data);
              $('#authority-update-modal').modal("hide");
            }
          }).catch(e => {
            alert(e);
          })
    },
    clickRadioBtn(flag){
      this.useYn = flag;
    }
  }
}
</script>

<style scoped>

</style>