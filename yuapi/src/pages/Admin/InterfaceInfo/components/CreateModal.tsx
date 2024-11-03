import {
  type ProColumns,
  ProTable,
} from '@ant-design/pro-components';
import { Modal } from 'antd';
import React from 'react';

export type Props = {
  columns: ProColumns<API.InterfaceInfo>[];
  onCancel: () => void;
  onSubmit: (values:API.InterfaceInfo) => Promise<void>;
  visible:boolean;
};

const CreateModal: React.FC<Props> = (props) => {
  const {visible,columns,onCancel,onSubmit} = props;
  return (
    // 这里visiable属性被弃用了，用open取代
    <Modal open={visible} footer={null} onCancel={()=>onCancel?.()}>
      <ProTable type="form" columns={columns} onSubmit={
        async (value) => {
          onSubmit?.(value);
        }
      }/>;
    </Modal>
  );
};

export default CreateModal;
