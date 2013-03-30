package Game.base;

public class Timer {

    /**
     * Timer speed.
     */
    private float speed = 1.0F;
    public float ticksPerSecond = 0.0F;
    public int ticksElapsed = 0;
    public float ticksPartialElapsed = 0.0F;
    public float ticksPartialRender = 0.0F;
    public double timeLastHRTSeconds = 0.0D;
    public long timeLastHRTMilliseconds = 0L;
    public long timeLastSyncHRTMilliseconds = 0L;
    public long timeLastSyncMilliseconds = 0L;
    public double timeSyncAdjustment = 1.0D;
    public long counter = 0L;

    public Timer(float ticksPerSecond) {
        this.ticksPerSecond = ticksPerSecond;
        this.timeLastSyncMilliseconds = Time.HRMILLISECONDS.getCurrent();
        this.timeLastSyncHRTMilliseconds = Time.MILLISECONDS.getCurrent();
    }

    public void update() {

        long SyncMilliseconds = Time.HRMILLISECONDS.getCurrent();
        long SyncMillisecondsDifference = SyncMilliseconds - this.timeLastSyncMilliseconds;
        long SyncHRTMilliseconds = Time.MILLISECONDS.getCurrent();
        double SyncHRTSeconds = (double) SyncHRTMilliseconds / 1000.0D;

        if (SyncMillisecondsDifference <= 1000L && SyncMillisecondsDifference >= 0L) {

            this.counter += SyncMillisecondsDifference;

            if (this.counter > 1000L) {
                long SyncHRTDifference = SyncHRTMilliseconds - this.timeLastSyncHRTMilliseconds;
                double SyncAdjustmentModifier = (double) this.counter / (double) SyncHRTDifference;

                this.timeSyncAdjustment += (SyncAdjustmentModifier - this.timeSyncAdjustment) * 0.20000000298023224D;
                this.timeLastSyncHRTMilliseconds = SyncHRTMilliseconds;
                this.counter = 0L;

            } else if (this.counter < 0L) {
                this.timeLastSyncHRTMilliseconds = SyncHRTMilliseconds;
            }
        } else {
            this.timeLastHRTSeconds = SyncHRTSeconds;
        }

        this.timeLastSyncMilliseconds = SyncMilliseconds;
        double modifier = (SyncHRTSeconds - this.timeLastHRTSeconds) * this.timeSyncAdjustment;

        this.timeLastHRTSeconds = SyncHRTSeconds;

        if (modifier < 0.0D) {
            modifier = 0.0D;
        }
        if (modifier > 1.0D) {
            modifier = 1.0D;
        }

        this.ticksPartialElapsed = (float) ((double) this.ticksPartialElapsed + modifier * (double) this.speed * (double) this.ticksPerSecond);
        this.ticksElapsed = (int) this.ticksPartialElapsed;
        this.ticksPartialElapsed -= (float) this.ticksElapsed;

        if (this.ticksElapsed > 10) {
            this.ticksElapsed = 10;
        }

        this.ticksPartialRender = this.ticksPartialElapsed;
    }
}
