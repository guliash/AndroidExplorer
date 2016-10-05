# AndroidExplorer
This project contains some simple examples of using Android features

No matter which broadcast you send they are all received. But if you send an ordered broadcast then order is determined by priority (default is 0, higher called before lower). Use abortBroadcast to cancel (can be called only if sendOrderedBroadcast was used, otherwise throws exception).