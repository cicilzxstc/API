import {
  type ProColumns,
  ProTable,
} from '@ant-design/pro-components';
import { Modal } from 'antd';
import React, {useEffect, useRef} from 'react';
import {ProFormInstance} from "@ant-design/pro-form/lib";

export type Props = {
  values:API.InterfaceInfo;
  columns: ProColumns<API.InterfaceInfo>[];
  onCancel: () => void;
  onSubmit: (values:API.InterfaceInfo) => Promise<void>;
  visible:boolean;
};

const UpdateModal: React.FC<Props> = (props) => {
  const {values,visible,columns,onCancel,onSubmit} = props;

  const formRef = useRef<ProFormInstance>();

  useEffect(()=>{
    if(formRef){
      formRef.current?.setFieldsValue(values);
    }
  },[values])

  return (
    // 这里visiable属性被弃用了，用open取代
    <Modal open={visible} footer={null} onCancel={()=>onCancel?.()}>
      <ProTable type="form" formRef={formRef} columns={columns} onSubmit={
        async (value) => {
          onSubmit?.(value);
        }
      }/>;
    </Modal>
  );
};

export default UpdateModal;
