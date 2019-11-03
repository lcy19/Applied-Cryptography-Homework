对于SM4一篇清晰的介绍[文章](https://neuqzxy.github.io/2017/06/15/欣仔带你零基础入门SM4加密算法/). SM4 是一种分组加密算法，明文长度为128 bit, 密文长度为128 bit,处理的基本单位是32 bit，主要部件为S盒。
![SM4算法流程图](https://i.loli.net/2019/11/03/z1ISdNfPFn8A3xm.png)
1. 加密过程：
    1) 32轮迭代
    2) 倒序操作
2. 解密过程
    1) 32轮迭代
    2) 倒序操作

加解密的唯一区别在于 **32轮密钥的使用顺序相反**。为了实现这一机制，需要倒序操作。

## SM4加密可逆证明
明文 M( x<sub>0</sub>, x<sub>1</sub>, x<sub>2</sub>, x<sub>3</sub>)， 经过32次轮函数后生成 ( x<sub>32</sub>, x<sub>33</sub>, x<sub>34</sub>, x<sub>35</sub>)，再次倒序后即密文C( y<sub>0</sub>, y<sub>1</sub>, y<sub>2</sub>, y<sub>3</sub>) = R( x<sub>32</sub>, x<sub>33</sub>, x<sub>34</sub>, x<sub>35</sub>)

易知y<sub>4</sub> = y<sub>0</sub> ⊕L(𝜏(y<sub>1</sub> ⊕ y<sub>2</sub> ⊕ y<sub>3</sub>)) = x<sub>31</sub>

```math
\begin{array}{l}
\quad  y_0 ⊕ L(\tau(y_1 ⊕ y_2 ⊕ y_3))\\
= x_{35} ⊕ L(\tau(x_{34} ⊕ x_{33} ⊕ x_{32}))\\
= x_{31} ⊕ L(\tau(x_{32} ⊕ x_{33} ⊕ x_{34}))  ⊕ L(\tau(x_{34} ⊕ x_{33} ⊕ x_{32}))\\
= x_{31} \\
\end{array}
```
依次递推，可得
```math
(x_0, x_1, x_2, x_3) = R(y_{32}, y_{33}, y_{34}, y_{35})
```
SM4加密可逆性证毕。

