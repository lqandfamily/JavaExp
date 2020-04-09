package com.lq.exp3.db;

import com.lq.exp3.entity.Inventory;

import java.io.*;

public class TextStatement<E> implements IStatement<E> {
    private BufferedReader br;
    private BufferedWriter bw;
    private File file;

    /**
     * 重新获取流的无奈操作,流读取一次就没了
     *
     * @return BufferedReader
     * @throws FileNotFoundException E
     */
    private BufferedReader getBufferedReader() throws FileNotFoundException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    }

    private BufferedWriter getBufferedWrite() throws FileNotFoundException {
        return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
    }

    public TextStatement(File file) throws FileNotFoundException {
        this.file = file;
        this.br = getBufferedReader();
//        this.bw = getBufferedWrite();
    }

    @Override
    public IResult<E> executeSelAll(Class<E> eClass) throws IOException {
        IResult<E> result = new TextResult<E>();
        //todo 没有找到好的数据存放回do的方法，阔能会考虑反射
        //获取泛型
        System.out.println("泛型：" + eClass.getTypeName());
        String type = eClass.getTypeName();

        /*******************************Test****************************************/
        String line = "";
        while ((line = br.readLine()) != null) {
            //装配数据
            String[] datas = line.split("\\s+");
            System.out.println("rowData:" + datas[0] + "," + datas[1] + ',' + datas[2] + ',' + datas[3]);
            result.add((E) new Inventory(datas[0], Integer.parseInt(datas[1]), datas[1], datas[2]));
        }

        System.out.println("读取完毕!");
        //重新获取流
        br = getBufferedReader();
        return result;
    }

    @Override
    public IResult<E> executeSel(Class<E> eClass, IWhereCallback where) throws IOException {
        IResult<E> result = new TextResult<>();
        /*******************************Test****************************************/
        String line = "";
        while ((line = br.readLine()) != null) {
            //装配数据
            String[] datas = line.split("\\s+");
            System.out.println("rowData:" + datas[0] + "," + datas[1] + ',' + datas[2] + ',' + datas[3]);
            Inventory inventory =  new Inventory(datas[0], Integer.parseInt(datas[1]), datas[1], datas[2]);
            if(where.where(inventory)){
                System.out.println("add:" + inventory.getItemNumber());
                result.add((E) inventory);
            }
        }

        System.out.println("读取完毕!");
        //重新获取流
        br = getBufferedReader();
        return result;
    }

    @Override
    public IResult<E> executeAdd(Object obj) {
        return null;
    }

    @Override
    public IResult<E> executeUpd(IWhereCallback where) {
        return null;
    }

    @Override
    public IResult<E> executeDel(IWhereCallback where) {
        return null;
    }
}
