package cn.edu.swufe.dice_of_coc;

public class RandomT {

    //获得随机AdB的方法
    public static int RandAdB(int A,int B){
        int fin = 0;
        for (int i = 1; i <= A; i++){
            int ed = 1+(int)(Math.random()*B);
            fin=fin+ed;
        }

        return fin;
    }

    //随机分配随机数的方法
    public static int[] Randthree(int A){
        int[] re={0,0,0};
        //先第一次随机获得第一个属性
        int ed1 = 1+(int)(Math.random()*A);
        re[0]=A;
        A=A-ed1;
        //第二次随机获得第二个属性
        int ed2 = 1+(int)(Math.random()*A);
        re[1]=A;
        A=A-ed2;
        //第三个属性
        re[2]=A;
        //返回一个数组
        return re;
    }
}
