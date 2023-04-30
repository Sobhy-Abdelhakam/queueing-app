package com.example.queueingapp;

public class DD {

    private double u;  // u is the service rate
    private double λ;  // λ is the interarrival rate
    private int k;  // k is the size of the system where there is one should to be balked
    private int ti; // ti is the time of occurrence of first balk
    private int Wqn;     // Wqn is the waiting time in the system
    private int nt;  // nt is the number of customers in the system is time t
    public int M;
    private int ni; // the first balked person

    public DD(double u, double λ, int k) {
        this.u = u;
        this.λ = λ;
        this.k = k;
        this.ti = 0;
    }


    // ============ methods of Case 1 (λ > u) ============
    public int get_ti() {
        int init_ti = (int) Math.round(((double) (this.k) - (this.u / this.λ)) / (this.λ - this.u));
        for (int try_ti = (int) (init_ti - (1 / λ));; try_ti -= (1 / λ)) {
            int first = (int) (λ * try_ti);
            double second = ((u * try_ti) - u / λ);
            // Round up if the number like 7.999 - otherwise round down
            if (second > (int) second + 0.9) {
                second = (int) second + 1;
            } else {
                second = (int) second;
            }
            int newK = (int) (first - second);
//            System.out.println("new k " + newK + " try ti = " + try_ti + "first = " + first+ " second = " + second);
            if (newK != k) {

                ti = try_ti + (int) (1 / λ);
                return (ti);
            }
        }
    }

    public boolean is_special_case() {
        return (1 / u) % (1 / λ) == 0;
    }

    // Number of people at a given time
    public void getni() {
        ni = (int) (λ * ti);
    }

    // time given by the user to calculate n(t)
    public int get_nt(double t) {
        if ((1 / λ) > t) {
            nt = 0;
        }
        else if ((1 / λ) <= t && t < ti) {
            nt= (int)(λ*t) - (int)((u*t)-(u/λ));
        }
        else if (t >= get_ti()) {
            nt = k-1;
        }
        return (nt);
    }


    public int get_Wqn(double n) {
        boolean special_case = is_special_case();
        getni();
        if (special_case) {
            if (n == 0) {
                Wqn = 0;
            } else if (n < λ*get_ti()) {
                Wqn = (int) ((1 / u - 1 / λ) * (n - 1));
            } else {
                Wqn = (int) ((1 / u - 1 / λ) * (n - 2));
            }
        } else {
            if (n == 0) {
                Wqn = 0;
            } else if (n < ni) {

                Wqn = (int) ((1 / u - 1 / λ) * (n - 1));
            } else {

                if ((n - ni - (1 / λ)) % (3) == 0) {
                    Wqn = (int) ((1 / u - 1 / λ) * (ni - 3));
                } else {
                    Wqn = (int) ((1 / u - 1 / λ) * (ni - 2));
                }
            }
        }
        return Wqn;
    }

    // =====================================================================================================
    // ============ methods of Case 2 (λ <= u) ============
    // =====================================================================================================
    public int get_ti_2() {
        ti = (int) (M / (u - λ));
        return (int) ti;
    }

    public int get_nt_2(double t) { // printing n(t) states
        if (t < get_ti_2()) {
            nt = (int) Math.floor(M + (λ * t) - (u * t));
        } else {
            if (t % (1 / λ) == 0) {
                nt = 1;
            } else {
                nt = 0;
            }
        }
        return nt;
    }

    public double get_Wqn_2(double n) { // printing Wq(n) states
        if (n == 0) {
            Wqn = (int) Math.round((M - 1) / (2 * u));
        } else if (n <= (λ * ti)) {
            Wqn = (int) ((M - 1 + n) * (1 / u) - n * (1 / λ));
        } else {
            Wqn = 0;
        }
        return Wqn;
    }

}